import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model';
import { UserHttpService } from '../user-http.service';

@Component({
  selector: 'app-liste-user',
  templateUrl: './liste-user.component.html',
  styleUrls: ['./liste-user.component.scss'],
})
export class ListeUserComponent {
  constructor(
    private listeUserService: UserHttpService,
    private router: Router
  ) {}

  listU(): Array<User> {
    return this.listeUserService.findAll();
  }

  goToAdd() {
    this.router.navigate(['gestionnaire/utilisateurs/mon-utilisateur']);
  }

  goToEdit(id: number) {
    this.router.navigate([
      'gestionnaire/utilisateurs/mon-utilisateur/:id' + id,
    ]);
  }

  remove(id: number): void {
    console.log('Test');
    this.listeUserService.remove(id);
    console.log('Test2');
  }
}
