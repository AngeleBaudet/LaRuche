import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Production, Ruche } from 'src/app/model';
import { RucheHttpService } from 'src/app/Ruches/ruche-http.service';
import { ProductionHttpService } from '../production-http.service';

@Component({
  selector: 'app-liste-production',
  templateUrl: './liste-production.component.html',
  styleUrls: ['./liste-production.component.scss']
})
export class ListeProductionComponent {

  constructor(private listeProductionsService:ProductionHttpService, 
    private router: Router, 
    private listeRuchesService:RucheHttpService){
    this.listeProductionsService.load()
  }

  listP():Array<Production> {
    return this.listeProductionsService.findAll();
  }

  goToAdd() {
    this.router.navigate([ 'unbeelievable/production/ma-production']);
  }

  goToEdit(id: number){
    this.router.navigate([ 'unbeelievable/production/ma-production/'+id]);
  }

  remove(id: number) : void {
    this.listeProductionsService.remove(id);
  }

  listR(): Array<Ruche> {
    return this.listeRuchesService.findAll();
  }

  afficher() : boolean {
    if (this.listR().length === 0){
      return false;
    } else {
      return true;
    }
  }
}
