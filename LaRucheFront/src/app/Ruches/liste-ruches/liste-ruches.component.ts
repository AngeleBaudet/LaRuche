import { Component } from '@angular/core';
import { Ruche } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'liste-ruches',
  templateUrl: './liste-ruches.component.html',
  styleUrls: ['./liste-ruches.component.scss']
})
export class ListeRuchesComponent {

  constructor(private listeRuchesService:RucheHttpService){

  }

  listR():Array<Ruche> {
    return this.listeRuchesService.findAll();
  }

  add() {
  }

  edit(){
  }

  remove(id: number) : void {
    this.listeRuchesService.remove(id);
  }
}
