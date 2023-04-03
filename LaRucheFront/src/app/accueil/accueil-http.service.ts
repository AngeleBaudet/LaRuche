import { Injectable, Query } from '@angular/core';
import { Ruche } from '../model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';
import { RucheHttpService } from '../Ruches/ruche-http.service';

@Injectable({
  providedIn: 'root'
})
export class AccueilHttpService {

  private rucheApiPath: string;
  listRucheVulnerabilite: Array<Ruche> = new Array<Ruche>;

  listRucheNourissage: Array<Ruche> = new Array<Ruche>;

  constructor(private http: HttpClient, 
    private rucheService: RucheHttpService,private globalService: GlobalService)
    { 
    this.rucheApiPath = globalService.apiPath + "ruche";
}

findRucheByVulnerabilite(){
  return this.http.get<Array<Ruche>>(this.rucheApiPath + "/vulnerabilite");
}

findRucheByNourissage(){
  return this.http.get<Array<Ruche>>(this.rucheApiPath + "/nourrissage");
}

}
