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
    console.log(this.allowed());
    console.log(this.connexionService.connectedUser.type);

    this.route.params.subscribe((params) => {
      this.vuln = params['vuln'];
      this.nourissage = params['nourrir'];
    });
  }

  listR(): Array<Ruche> {
    let listRuches: Array<Ruche> = new Array<Ruche>();
    let listAll: Array<Ruche> = this.listeRuchesService.findAll();
    if (this.vuln) {
      listAll.forEach((ruche) => {
        if (ruche.vulnerabilite) {
          listRuches.push(ruche);
        }
        // this.accueilService.mesRuchesVulnerables = false;
      });
      return listRuches;
    } else if (this.nourissage == 'nourrir') { //a creuser
      console.log(this.accueilService.listRucheNourissage);
      return this.accueilService.listRucheNourissage;
    }
    return this.listeRuchesService.findAll();
  }

  goToAdd() {
    this.router.navigate(['gestionnaire/ruche/ma-ruche']);
  }

  goToEdit(id: number) {
    this.router.navigate(['gestionnaire/ruche/ma-ruche/' + id]);
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
