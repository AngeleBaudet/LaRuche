import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Ruche } from '../model';
import { GlobalService } from '../global.service';

@Injectable({
  providedIn: 'root'
})
export class RucheHttpService {
  
  private ruches: Array<Ruche> = new Array<Ruche>();
  private rucheApiPath: string;

  constructor(private http: HttpClient, private globalService: GlobalService) {
    this.rucheApiPath = globalService.apiPath + "/ruche";
    this.load();
  }

  findAll(): Array<Ruche> {
    return this.ruches;
  }

  findById(id: number): Observable<Ruche> {
    return this.http.get<Ruche>(this.rucheApiPath + "/" + id);
  }

  create(ruche: Ruche): void {
    this.http.post<Ruche>(this.rucheApiPath, ruche).subscribe(resp => {
      this.load();
    });
  }

  update(ruche: Ruche): void {
    this.http.put<Ruche>(this.rucheApiPath + "/" +  ruche.id, ruche).subscribe(resp => {
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
