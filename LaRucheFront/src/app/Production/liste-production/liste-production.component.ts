import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Production } from 'src/app/model';
import { ProductionHttpService } from '../production-http.service';

@Component({
  selector: 'app-liste-production',
  templateUrl: './liste-production.component.html',
  styleUrls: ['./liste-production.component.scss']
})
export class ListeProductionComponent {

  constructor(private listeProductionsService:ProductionHttpService, private router: Router){
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
}
