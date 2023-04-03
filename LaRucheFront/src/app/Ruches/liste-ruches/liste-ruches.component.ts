import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Ruche } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'liste-ruches',
  templateUrl: './liste-ruches.component.html',
  styleUrls: ['./liste-ruches.component.scss']
})
export class ListeRuchesComponent {

  constructor(private listeRuchesService:RucheHttpService, private router: Router){

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
}
