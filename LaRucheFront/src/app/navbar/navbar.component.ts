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

deconnexion(){
  this.connexionService.connectedUser = null;
}
}

