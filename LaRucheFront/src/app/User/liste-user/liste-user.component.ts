import { Component } from '@angular/core';
import { ActivatedRoute, Router, UrlSegment } from '@angular/router';
import { User } from 'src/app/model';
import { UserHttpService } from '../user-http.service';

@Component({
  selector: 'app-liste-user',
  templateUrl: './liste-user.component.html',
  styleUrls: ['./liste-user.component.scss'],
})
export class ListeUserComponent {
  
  type: string = null;

  constructor(
    private listeUserService: UserHttpService,
    private router: Router, 
    private route: ActivatedRoute
  ) {

    this.route.url.subscribe(urls => {
      let last: UrlSegment = urls[urls.length-1];
      
      if(last.path == 'recolteurs') {
        this.type = "recolteur";
      } else if(last.path == 'clients') {
        this.type = "client";
      } else {
        this.type = null;
      }

    });
  
  }

  listU(): Array<User> {
    if(this.type) {
      return this.listeUserService.findAll().filter(u => u.type == this.type);
    }


    return this.listeUserService.findAll();
  }

  goToAdd() {
    this.router.navigate(['gestionnaire/utilisateurs/mon-utilisateur']);
  }

  goToEdit(id: number) {
    this.router.navigate([
      'gestionnaire/utilisateurs/mon-utilisateur/' + id,
    ]);
  }

  remove(id: number): void {
    console.log('Test');
    this.listeUserService.remove(id);
    console.log('Test2');
  }
}
