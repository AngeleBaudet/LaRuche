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
  styleUrls: ['./accueil.component.scss'],
})
export class AccueilComponent {
  private rucheApiPath: string;
  listRucheVulnerabilite: Array<Ruche> = new Array<Ruche>();
  listRucheNourissage: Array<Ruche> = new Array<Ruche>();

  /*   listRucheNourissage: Array<Ruche> = new Array<Ruche>; */

  constructor(private accueilService: AccueilHttpService) {}


  ngOnInit() {
    this.rucheNourissage();
    this.rucheVulnerabilite();
  }


  rucheNourissage(): void{
    this.accueilService.findRucheByNourissage().subscribe({
      next: (ruches) => {
        this.listRucheNourissage = ruches;
        //do something with the ruches
        
      }
     
    });
    console.log(this.listRucheNourissage.length);
} 

  rucheVulnerabilite(): void {
    this.accueilService.findRucheByVulnerabilite().subscribe({
      next: ruches=>{ this.listRucheVulnerabilite = ruches
        //do something with the ruches
      }
      
    });
    console.log(this.listRucheVulnerabilite.length);
    
  }
}
