import { Component } from '@angular/core';
import { RucheHttpService } from '../Ruches/ruche-http.service';
import { AccueilHttpService } from './accueil-http.service';
import { Gestionnaire, Ruche } from '../model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';

@Component({
  selector: 'accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent {


  private rucheApiPath: string;
  listRucheVulnerabilite: Array<Ruche> = new Array<Ruche>;

/*   listRucheNourissage: Array<Ruche> = new Array<Ruche>; */

  constructor(private AccueilService: AccueilHttpService){ }

findRucheNourissage(id:number): Array<Ruche>{
  listRucheNourissage : Array<Ruche> = this.AccueilService.findRucheByRecolteur(id);

  return listRucheNourissage;

}

findRucheVulnerabilite(id: number): Array<Ruche> {
  let listRucheVulnerabilite: Array<Ruche> = [];

  this.AccueilService.findRucheByRecolteur(id).subscribe(
    (ruches: Ruche[]) => {
      listRucheVulnerabilite = ruches.filter(ruche => ruche.vulnerabilite!= null);
    },
    (error: any) => {
      console.log("Error fetching Ruches: ", error);
    }
  );

  return listRucheVulnerabilite;
}
}


