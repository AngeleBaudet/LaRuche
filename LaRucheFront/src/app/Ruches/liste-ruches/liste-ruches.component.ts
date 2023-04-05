import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ruche } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';
import { ConnexionHttpService } from 'src/app/connexion/connexion-http.service';
import { AccueilHttpService } from 'src/app/accueil/accueil-http.service';

@Component({
  selector: 'liste-ruches',
  templateUrl: './liste-ruches.component.html',
  styleUrls: ['./liste-ruches.component.scss'],
})
export class ListeRuchesComponent {
  connectedType: string;
  listAll: Array<Ruche> = this.listeRuchesService.findAll();
  vuln: boolean = false;
  nourissage: string = '';

  constructor(
    private listeRuchesService: RucheHttpService,
    private router: Router,
    private connexionService: ConnexionHttpService,
    private route: ActivatedRoute,
    private accueilService: AccueilHttpService
  ) {
    this.connectedType = this.connexionService.connectedUser.type;
    console.log(this.listR().length);
    console.log(this.connexionService.connectedUser.type);
    this.listeRuchesService.load()

    this.route.params.subscribe((params) => {
      this.vuln = params['vuln'];
      this.nourissage = params['nourrir'];
    });
  }

  listR(): Array<Ruche> {
    let listRuches: Array<Ruche> = new Array<Ruche>();
    if (this.vuln) {
      this.listAll.forEach((ruche) => {
        if (ruche.vulnerabilite) {
          listRuches.push(ruche);
        }
      });
      return listRuches;
    } else if (this.nourissage == 'nourrir') { //a creuser
      console.log(this.accueilService.listRucheNourissage);
      return this.accueilService.listRucheNourissage;
    }
    return this.listeRuchesService.findAll();
  }

  goToAdd() {
    this.router.navigate(['unbeelievable/ruche/ma-ruche']);
  }

  goToEdit(id: number) {
    this.router.navigate(['unbeelievable/ruche/ma-ruche/' + id]);
  }

  remove(id: number): void {
    this.listeRuchesService.remove(id);
  }

  allowed(): boolean {
    if (this.connexionService.connectedUser.type === 'gestionnaire') {
      return true;
    }
    return false;
  }
}
