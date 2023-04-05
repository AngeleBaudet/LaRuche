import { Injectable } from '@angular/core';
import { User } from '../model';
import { HttpClient } from '@angular/common/http';
import { GlobalService } from '../global.service';
import { Observable, Subscription, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConnexionHttpService {
  //Sert a rien
  users: Array<User> = new Array<User>();

 // connectedUser: User = new User("gestionnaire",1,"ges1","ges1");
  connectedUser: User;

  private usersApiPath: string;

  constructor(private http: HttpClient, globalService: GlobalService) {
    // should be /user
    this.usersApiPath = globalService.apiPath + 'user';
    this.load();
    
  }
//Sert a rien
  findAll(): Array<User> {
    return this.users;
  }
//Sert a rien
  findById(id: number): Observable<User> {
    return this.http.get<User>(this.usersApiPath + '/' + id);
  }

  
  private load(): void {
    this.http.get<Array<User>>(this.usersApiPath).subscribe((resp) => {
      this.users = resp;
    });
  }

  //have a connexion function that targets /api/user/connexion with login and password query params
  connexion(login: string, password: string): Observable<User> {
    return this.http.get<User>(this.usersApiPath + '/' + 'connexion', {
      params: { login, password },
    });
  }

  allowed():boolean{
    if (this.connectedUser.type === 'gestionnaire'){
      return true;
    }
    return false;
  }

  
}
