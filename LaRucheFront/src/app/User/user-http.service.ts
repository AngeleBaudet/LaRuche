import { Injectable } from '@angular/core';
import { User, UserRequest } from '../model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalService } from '../global.service';

@Injectable({
  providedIn: 'root',
})
export class UserHttpService {
  users: Array<User> = new Array<User>();

  private usersApiPath: string;

  constructor(private http: HttpClient, globalService: GlobalService) {
    this.usersApiPath = globalService.apiPath + 'user';
    this.load();
  }

  findAll(): Array<User> {
    return this.users;
  }

  findById(id: number): Observable<User> {
    return this.http.get<User>(this.usersApiPath + '/' + id);
  }

  create(user: UserRequest): void {
    this.http.post<UserRequest>(this.usersApiPath, user).subscribe((resp) => {
      this.load();
    });
  }

  update(user: UserRequest): void {
    this.http
      .put<UserRequest>(this.usersApiPath + '/' + user.id, user)
      .subscribe((resp) => {
        this.load();
      });
  }

  remove(id: number): void {
    this.http
      .delete<boolean>(this.usersApiPath + '/' + id)
      .subscribe((resp) => {
        this.load();
      });
  }

  private load(): void {
    this.http.get<Array<User>>(this.usersApiPath).subscribe((resp) => {
      this.users = resp;
    });
  }
}
