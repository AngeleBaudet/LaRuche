import { Injectable, Query } from '@angular/core';
import { Ruche } from '../model';

import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';
import { RucheHttpService } from '../Ruches/ruche-http.service';
import { ConnexionHttpService } from '../connexion/connexion-http.service';

@Injectable({
  providedIn: 'root',
})
export class AccueilHttpService {
  private rucheApiPath: string;
  listRucheVulnerabilite: Array<Ruche> = new Array<Ruche>();

  listRucheNourissage: Array<Ruche> = new Array<Ruche>();

  constructor(
    private http: HttpClient,
    private rucheService: RucheHttpService,
    private globalService: GlobalService,
    private connexionService: ConnexionHttpService
  ) {
    this.rucheApiPath = globalService.apiPath + 'ruche';
    this.load();
  }

  findRucheByVulnerabilite() {
    if (this.connexionService.connectedUser.type === 'recolteur') {
      return this.http.get<Array<Ruche>>(
        this.rucheApiPath +
          '/vulnerabilite/' +
          this.connexionService.connectedUser.id
      );
    } else
      return this.http.get<Array<Ruche>>(this.rucheApiPath + '/vulnerabilite');
  }

/*     findRucheByNourissage() {
    return this.http.get<Array<Ruche>>(this.rucheApiPath + '/nourrissage');
  } */

  findRucheByNourissage() {
    return this.listRucheNourissage;
  }

  private load(): void {
    this.http
      .get<Array<Ruche>>(this.rucheApiPath + '/nourrissage')
      .subscribe((resp) => {
        this.listRucheNourissage = resp;
      });
  }
}
