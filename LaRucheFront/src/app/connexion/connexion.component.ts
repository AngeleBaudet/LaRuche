import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gestionnaire, User } from '../model';
import { ConnexionHttpService } from './connexion-http.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss'],
})
export class ConnexionComponent {
  loginForm: FormGroup;
  isSubmitted = false;
  wrongCredents: boolean; 

  constructor(
    private formBuilder: FormBuilder,
    private connexionService: ConnexionHttpService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  get formControls() {
    return this.loginForm.controls;
  }

  seConnecter() {
    console.log(this.loginForm.value);
    const login = this.loginForm.get('login').value;
    const pwd = this.loginForm.get('password').value;
    this.connexionService.connexion(login, pwd).subscribe({
      next: (user) => {
        if (user) {
          this.connexionService.connectedUser = user;
          this.router.navigateByUrl('/gestionnaire');
          console.log(this.connexionService.connectedUser.type)
        } else {
          console.log('not an existing user');
          this.wrongCredents=true;
        }
      },
    });
  }
}
