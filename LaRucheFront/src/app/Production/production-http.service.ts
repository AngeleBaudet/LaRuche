import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConnexionHttpService } from '../connexion/connexion-http.service';
import { GlobalService } from '../global.service';
import { Production, ProductionRequest, Ruche } from '../model';

@Injectable({
  providedIn: 'root',
})
export class ProductionHttpService {
  private productions: Array<Production> = new Array<Production>();
  private productionApiPath: string;
  private productionByRecolteur: Array<Production> = new Array<Production>();

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private connexionService: ConnexionHttpService
  ) {
    this.productionApiPath = globalService.apiPath + 'production';
    this.load();
  }

  findAll(): Array<Production> {
    if (this.connexionService.connectedUser.type === 'recolteur') {
      return this.productionByRecolteur;
    } else return this.productions;
  }

  findById(id: number): Observable<Production> {
    return this.http.get<Production>(this.productionApiPath + '/' + id);
  }

  create(production: ProductionRequest): void {
    this.http
      .post<ProductionRequest>(this.productionApiPath, production)
      .subscribe((resp) => {
        this.load();
      });
  }

  update(production: ProductionRequest): void {
    this.http
      .put<ProductionRequest>(
        this.productionApiPath + '/' + production.id,
        production
      )
      .subscribe((resp) => {
        this.load();
      });
  }

  remove(id: number): void {
    this.http
      .delete<boolean>(this.productionApiPath + '/' + id)
      .subscribe((resp) => {
        this.load();
      });
  }

  load(): void {
    this.http
      .get<Array<Production>>(this.productionApiPath)
      .subscribe((resp) => {
        this.productions = resp;
      });
    if (this.connexionService.connectedUser.type === 'recolteur') {
      this.http
        .get<Array<Production>>(
          this.productionApiPath +
            '/by-recolteur/' +
            this.connexionService.connectedUser.id
        )
        .subscribe((resp) => (this.productionByRecolteur = resp));
    }
  }
}
