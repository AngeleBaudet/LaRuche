import { Component } from '@angular/core';
import { RucheHttpService } from '../Ruches/ruche-http.service';
import { AccueilHttpService } from './accueil-http.service';
import { Gestionnaire, Ruche } from '../model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';
import { Router } from '@angular/router';
import { ConnexionHttpService } from '../connexion/connexion-http.service';

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

  constructor(
    private accueilService: AccueilHttpService,
    private router: Router,
    private connexionService: ConnexionHttpService
  ) {}

  ngOnInit() {
    this.rucheNourissage();
    this.rucheVulnerabilite();
  }
  
  rucheNourissage(): void {
    this.accueilService.findRucheByNourissage().subscribe({
      next: (ruches) => {
        this.listRucheNourissage = ruches;
        //do something with the ruches
      },
    });
  } 

  /*
  rucheNourissage(): void {
    this.listRucheNourissage = this.accueilService.listRucheNourissage;
  }*/

  rucheVulnerabilite(): void {
    this.accueilService.findRucheByVulnerabilite().subscribe({
      next: (ruches) => {
        this.listRucheVulnerabilite = ruches;
        //do something with the ruches
      },
    });
  }

  //boutons au niveau des alertes
  goToRucheN() {
    this.router.navigate(['unbeelievable/ruche/nourrissage']);
  }

  goToRucheVulnerables() {
    this.router.navigate(['unbeelievable/ruche/vulnerabilite']);
  }

  //Boutons en bas 
  goToRuche() {
    this.router.navigate(['unbeelievable/ruche']);
  }

  goToProduction() {
    this.router.navigate(['unbeelievable/production']);
  }

  goToUsers() {
    this.router.navigate([ 'unbeelievable/utilisateurs']);
  }

  allowed():boolean{
    return this.connexionService.allowed();
  }
}
