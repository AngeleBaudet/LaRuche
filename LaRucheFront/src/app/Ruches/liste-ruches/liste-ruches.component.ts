import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ruche, RucheRequest } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';
import { ConnexionHttpService } from 'src/app/connexion/connexion-http.service';
import { AccueilHttpService } from 'src/app/accueil/accueil-http.service';
import { ProductionHttpService } from 'src/app/Production/production-http.service';

@Component({
  selector: 'liste-ruches',
  templateUrl: './liste-ruches.component.html',
  styleUrls: ['./liste-ruches.component.scss'],
})
export class ListeRuchesComponent {
  connectedType: string;
  listAll: Array<Ruche> = this.listeRuchesService.findAll();
  tri: string;
  nourissage: string = '';
  listRucheNourissage: Array<Ruche> = new Array<Ruche>();

  constructor(
    private listeRuchesService: RucheHttpService,
    private router: Router,
    private connexionService: ConnexionHttpService,
    private route: ActivatedRoute,
    private accueilService: AccueilHttpService,
    private prodService: ProductionHttpService
  ) {
    this.connectedType = this.connexionService.connectedUser.type;
    this.listeRuchesService.load();
    this.route.params.subscribe((params) => {
      console.log(params);
      this.tri = params['tri'];
    });
  }

  listR(): Array<Ruche> {
    let listRuches: Array<Ruche> = new Array<Ruche>();
    if (this.tri === 'vulnerabilite') {
      this.listAll.forEach((ruche) => {
        if (ruche.vulnerabilite) {
          listRuches.push(ruche);
        }
      });
      return listRuches;
    } else if (this.tri == 'nourrissage') {
      return this.accueilService.findRucheByNourissageBis();
    }
    return this.listeRuchesService.findAll();
  }

  nourrissage(idRuche: number): boolean {
    let ruche = this.accueilService
      .findRucheByNourissageBis()
      .find((r) => r.id == idRuche);

    if (ruche) {
      return true;
    } else console.log(idRuche);
    return false;
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

  Division(id: number) {
    let rucheMere: RucheRequest;
    let rucheFille: RucheRequest;
    this.listeRuchesService.findById(id).subscribe((resp) => {
      if (resp.cadre % 2 === 0) {
        rucheMere = new RucheRequest(
          resp.id,
          resp.cadre / 2,
          resp.limite,
          resp.vulnerabilite,
          resp.recolteur.id
        );
        rucheFille = new RucheRequest(
          null,
          resp.cadre / 2,
          resp.limite,
          resp.vulnerabilite,
          resp.recolteur.id
        );
      } else {
        rucheMere = new RucheRequest(
          resp.id,
          resp.cadre / 2 + 1,
          resp.limite,
          resp.vulnerabilite,
          resp.recolteur.id
        );
        rucheFille = new RucheRequest(
          null,
          resp.cadre / 2,
          resp.limite,
          resp.vulnerabilite,
          resp.recolteur.id
        );
      }
      this.listeRuchesService.create(rucheFille);

      this.listeRuchesService.update(rucheMere);
    });
  }

  allowed(): boolean {
    if (this.connexionService.connectedUser.type === 'gestionnaire') {
      return true;
    }
    return false;
  }
}
