import { Component } from '@angular/core';
import { ConnexionHttpService } from './connexion/connexion-http.service';
import { User } from './model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'LaRucheFront';

  constructor(private connexionService: ConnexionHttpService) {    
  }

  // transforme une fonction en attribut 
  get connectedUser() : User {
    return this.connexionService.connectedUser;
  }
}
