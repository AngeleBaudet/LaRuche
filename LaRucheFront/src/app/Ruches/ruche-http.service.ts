import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Ruche, RucheRequest } from '../model';
import { GlobalService } from '../global.service';

@Injectable({
  providedIn: 'root'
})
export class RucheHttpService {
  
  private ruches: Array<Ruche> = new Array<Ruche>();
  private rucheApiPath: string;

  constructor(private http: HttpClient, private globalService: GlobalService) {
    this.rucheApiPath = globalService.apiPath + "ruche";
    this.load();
  }

  findAll(): Array<Ruche> {
    return this.ruches;
  }

  findById(id: number): Observable<Ruche> {
    return this.http.get<Ruche>(this.rucheApiPath + "/" + id);
  }

  create(ruche: RucheRequest): void {
    this.http.post<RucheRequest>(this.rucheApiPath, ruche).subscribe(resp => {
      this.load();
    });
  }
 
  /*exemple requete correcte : {
    "cadre": 5,
    "limite": false,
    "vulnerabilite": "Pesticides",
    "recolteurId": 1
  }*/

  update(ruche: RucheRequest): void {
    this.http.put<RucheRequest>(this.rucheApiPath + "/" +  ruche.id, ruche).subscribe(resp => {
      this.load();
    });
  }

  remove(id: number): void {
    this.http.delete<boolean>(this.rucheApiPath + "/" + id).subscribe(resp => {
      this.load();
    });
  }

  private load(): void {
    this.http.get<Array<Ruche>>(this.rucheApiPath).subscribe(resp => {
        this.ruches = resp;
    })
  }
}
