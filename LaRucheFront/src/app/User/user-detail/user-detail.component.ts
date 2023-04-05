import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UserRequest } from 'src/app/model';
import { UserHttpService } from '../user-http.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmPasswordValidator } from './confirm-password.validator';
import { ConnexionHttpService } from 'src/app/connexion/connexion-http.service';

@Component({
  selector: 'user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss'],
})
export class UserDetailComponent {
  userForm: FormGroup;
  formValue: UserRequest = new UserRequest();
  selectedOption: String;
  submitted: boolean = false;

  compte:boolean;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserHttpService,
    private router: Router, //pour rediriger le bouton annuler (methode goToListRuche)
    private routes: ActivatedRoute, // pour récupérer le param
    private connexionService : ConnexionHttpService,
  ) {

  }
    //création du reactive form (+validation)

    ngOnInit() {
    this.userForm = this.formBuilder.group(
      {
        id: [""],
        login: ["",Validators.required],
        password: ["",Validators.required],
        confirmPassword: ["",Validators.required],
        type: ["",Validators.required],
      },
      { validator: ConfirmPasswordValidator('password', 'confirmPassword') }
    );
    this.routes.params.subscribe((params) => {
      if (params['id']) {
        this.edit(params['id']);
        if (this.connexionService.connectedUser.type==="recolteur"){
          this.compte=true;
        }  else {
          this.compte=false; 
        }
      }
    });
  }

  /*   passwordsMatch(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMatch: true };
  } */

  submit() {
    this.formValue = this.userForm.value;

    if (this.formValue.id) {
      this.userService.update(this.formValue);
      this.router.navigate(['unbeelievable']);
    } else {
      this.userService.create(this.formValue);
      this.router.navigate(['unbeelievable']);
      this.submitted = true;
    }
    this.submitted = true
    this.cancel();
  }

  cancel(): void {
    this.formValue = new UserRequest();
  }

  edit(id: number): void {
    this.userService.findById(id).subscribe((resp) => {
      this.formValue = new UserRequest(
        resp.id,
        resp.login,
        resp.password,
        resp.type
      );

      this.userForm = this.formBuilder.group({
        id: this.formBuilder.control(this.formValue.id),
        login: this.formBuilder.control(
          this.formValue.login,
          Validators.required
        ),
        password: this.formBuilder.control('', Validators.required),
        confirmPassword: this.formBuilder.control('', Validators.required),
        type: this.formBuilder.control(this.formValue.type),
      },
      { validator: ConfirmPasswordValidator('password', 'confirmPassword') });
    });
  }

  goToHome() {
    this.router.navigate(['unbeelievable']);
  }
}
