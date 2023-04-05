import { Component } from '@angular/core';
import { ConnexionHttpService } from '../connexion/connexion-http.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
sourisA: boolean = false;
sourisB: boolean = false;
sourisC: boolean = false;
sourisD: boolean = false;
sourisE: boolean = false;
sourisF: boolean = false;



constructor(private connexionService: ConnexionHttpService){}

idConnected:number=this.connexionService.connectedUser.id;

youAre:string = this.connexionService.connectedUser.login;

deconnexion(){
  this.connexionService.connectedUser = null;
}

allowed():boolean{
  if (this.connexionService.connectedUser.type === 'gestionnaire'){
    return true;
  }
  return false;
}
}

