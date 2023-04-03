import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Ruche } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';
import { ConnexionHttpService } from 'src/app/connexion/connexion-http.service';

@Component({
  selector: 'liste-ruches',
  templateUrl: './liste-ruches.component.html',
  styleUrls: ['./liste-ruches.component.scss']
})
export class ListeRuchesComponent {

  connectedType:string;

  constructor(private listeRuchesService:RucheHttpService, private router: Router, private connexionService: ConnexionHttpService){
    this.connectedType = this.connexionService.connectedUser.type;
    console.log(this.allowed());
    console.log(this.connexionService.connectedUser.type)
  }

  listR():Array<Ruche> {
    return this.listeRuchesService.findAll();
  }

  goToAdd() {
    this.router.navigate([ 'gestionnaire/ruche/ma-ruche']);
  }

  goToEdit(id: number){
    this.router.navigate([ 'gestionnaire/ruche/ma-ruche/'+id]);
  }

  remove(id: number) : void {
    this.listeRuchesService.remove(id);
  }

  allowed():boolean{
    if (this.connexionService.connectedUser.type === 'gestionnaire'){
      return true;
    }
    return false;
  }
}
