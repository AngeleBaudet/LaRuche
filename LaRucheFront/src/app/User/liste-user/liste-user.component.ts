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

  //Ajouter les bonnes joutes pour modifier et tout !
  goToAdd() {
    this.router.navigate(['gestionnaire']);
  }

  goToEdit(id: number) {
    this.router.navigate(['gestionnaire' + id]);
  }

  remove(id: number): void {
    this.listeUserService.remove(id);
  }
}
