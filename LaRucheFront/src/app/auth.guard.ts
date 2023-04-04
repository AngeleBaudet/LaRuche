import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { ConnexionHttpService } from './connexion/connexion-http.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private connexionService: ConnexionHttpService,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.connexionService.connectedUser == null) {
      this.router.navigate(['/connexion'], {
        queryParams: { returnUrl: state.url },
      });
      return false;
    }

    return true;
  }
}
