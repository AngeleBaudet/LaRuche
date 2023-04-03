import { Injectable } from '@angular/core';
import { User } from '../model';

@Injectable({
  providedIn: 'root',
})
export class ConnexionService {
  private users: Array<User> = new Array<User>();

  constructor() {
    this.users.push(new User(1, 'ges', 'ges'));
    this.users.push(new User(2, 'ges2', 'ges2'));
    this.users.push(new User(3, 'ges3', 'ges3'));
  }

  findAll(): Array<User> {
    return this.users;
  }

  findById(id: number): User {
    return this.users.find((u) => u.id == id);
  }


}
