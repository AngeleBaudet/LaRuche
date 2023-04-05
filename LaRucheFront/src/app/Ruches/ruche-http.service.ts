import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Ruche, RucheRequest } from '../model';
import { GlobalService } from '../global.service';
import { ConnexionHttpService } from '../connexion/connexion-http.service';

@Injectable({
  providedIn: 'root',
})
export class RucheHttpService {
  private ruches: Array<Ruche> = new Array<Ruche>();
  private rucheApiPath: string;
  private rucheByRecolteur: Array<Ruche> = new Array<Ruche>();

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private connexionService: ConnexionHttpService
  ) {
    this.rucheApiPath = globalService.apiPath + 'ruche';
    this.load();
  }

  findAll(): Array<Ruche> {
    if (this.connexionService.connectedUser.type === 'recolteur') {
      return this.rucheByRecolteur;
    } else return this.ruches;
  }

  findById(id: number): Observable<Ruche> {
    return this.http.get<Ruche>(this.rucheApiPath + '/' + id);
  }

  create(ruche: RucheRequest): void {
    this.http.post<RucheRequest>(this.rucheApiPath, ruche).subscribe((resp) => {
      this.load();
    });
  }


  update(ruche: RucheRequest): void {
    this.http
      .put<RucheRequest>(this.rucheApiPath + '/' + ruche.id, ruche)
      .subscribe((resp) => {
        this.load();
      });
  }

  remove(id: number): void {
    this.http
      .delete<boolean>(this.rucheApiPath + '/' + id)
      .subscribe((resp) => {
        this.load();
      });
  }

  load(): void {
    this.http.get<Array<Ruche>>(this.rucheApiPath).subscribe((resp) => {
      this.ruches = resp;
    });
    if (this.connexionService.connectedUser.type === 'recolteur') {
      this.http
        .get<Array<Ruche>>(
          this.rucheApiPath +
            '/by-recolteur-id/' +
            this.connexionService.connectedUser.id
        )
        .subscribe((resp) => (this.rucheByRecolteur = resp));
    }
  }

}
