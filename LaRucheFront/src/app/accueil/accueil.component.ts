import { Component } from '@angular/core';
import { RucheHttpService } from '../Ruches/ruche-http.service';
import { AccueilHttpService } from './accueil-http.service';
import { Gestionnaire, Ruche } from '../model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';
import { Router } from '@angular/router';
import { ConnexionHttpService } from '../connexion/connexion-http.service';
import { ProductionHttpService } from '../Production/production-http.service';

@Component({
  selector: 'accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss'],
})
export class AccueilComponent {
  private rucheApiPath: string;
  listRucheVulnerabilite: Array<Ruche> = new Array<Ruche>();
  listRucheNourissage: Array<Ruche> = new Array<Ruche>();
  listRucheDivision: Array<Ruche> = new Array<Ruche>();

  /*   listRucheNourissage: Array<Ruche> = new Array<Ruche>; */

  constructor(
    private accueilService: AccueilHttpService,
    private router: Router,
    private connexionService: ConnexionHttpService, 
    private rucheService: RucheHttpService,
    private productionService: ProductionHttpService
  ) {
    this.productionService.load();
    this.rucheService.load();
  }

  //question: pourquoi pas besoin du implements OnInit ? 
  ngOnInit() {
    this.rucheNourissage();
    this.rucheVulnerabilite();
    this.rucheDiviser();
  }
  
  rucheNourissage(): void {
    this.accueilService.findRucheByNourissage().subscribe({
      next: (ruches) => {
        if (this.connexionService.connectedUser.type === 'recolteur'){
        this.listRucheNourissage = ruches.filter(ruche => 
           ruche.recolteur.id == this.connexionService.connectedUser.id
        )
      } else this.listRucheNourissage = ruches
        //do something with the ruches
      },
    });
  } 

  rucheVulnerabilite(): void {
    this.accueilService.findRucheByVulnerabilite().subscribe({
      next: (ruches) => {
        this.listRucheVulnerabilite = ruches;
        //do something with the ruches
      },
    });
  }

  rucheDiviser(): void {
    this.accueilService.findRucheAll().subscribe({
      next: (ruches) => {

        
        if( this.connexionService.connectedUser.type === 'recolteur') {
          this.listRucheDivision = ruches.filter( 
            ruche => ruche.cadre>5
            && ruche.recolteur.id == this.connexionService.connectedUser.id)
        }
        else {
        this.listRucheDivision = ruches.filter( ruche => ruche.cadre>5)}
        //do something with the ruches*/

       // this.listRucheDivision = ruches.filter( ruche => ruche.cadre>5)
      },
    });
  }

  //c'est là où on fait des choses en double avec méthode listR dans liste ruche
  /*
  rucheDivision(){    
    let listAll: Array<Ruche> = this.rucheService.findAll();
    console.log(listAll)
    listAll.forEach((ruche) => {
      if (ruche.cadre>5) {
        this.listRucheDivision.push(ruche);
      }
    });
  }*/

  //boutons au niveau des alertes
  goToRucheN() {
    this.router.navigate(['unbeelievable/ruche/nourrissage']);
  }

  goToRucheVulnerables() {
    this.router.navigate(['unbeelievable/ruche/vulnerabilite']);
  }

  goToRucheDiviser(){
    this.router.navigate(['unbeelievable/ruche/division']);
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
