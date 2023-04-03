import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalService } from '../global.service';
import { Production, ProductionRequest } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ProductionHttpService {

  private productions : Array<Production> = new Array<Production>();
  private productionApiPath:string;

  constructor(private http:HttpClient, private globalService:GlobalService) { 
    this.productionApiPath=globalService.apiPath + "production";
    this.load();
  }

  findAll():Array<Production> {
    return this.productions;
  }

  findById(id: number) : Observable<Production> {
    return this.http.get<Production>(this.productionApiPath+"/"+id);
  }

  create(production: ProductionRequest):void {
    this.http.post<ProductionRequest>(this.productionApiPath,production).subscribe(resp => {
      this.load();
    });
  }

  update(production: ProductionRequest) : void {
    this.http.put<ProductionRequest>(this.productionApiPath+"/"+ production.id,production).subscribe(resp => {
      this.load();
    });
  }

  remove(id:number):void {
    this.http.delete<boolean>(this.productionApiPath+"/"+id).subscribe(resp => {
      this.load();
    });
  }

  private load(): void {
    this.http.get<Array<Production>>(this.productionApiPath).subscribe(resp => {
        this.productions = resp;
    })
  }
}
