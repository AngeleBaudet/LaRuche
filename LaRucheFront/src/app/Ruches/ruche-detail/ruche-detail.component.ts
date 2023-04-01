import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Recolteur, Ruche, Vulnerabilite } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'ruche-detail',
  templateUrl: './ruche-detail.component.html',
  styleUrls: ['./ruche-detail.component.scss']
})
export class RucheDetailComponent {

  rucheForm: FormGroup;
  vulnerabilite = Object.values(Vulnerabilite).filter(value => isNaN(Number(value)));
  /* Obligé de mettre le isNaN filtre car sinon il me retourne  
  [   0,  1,  2,  3, "Parasites",  "Pesticides",  "Predateurs",  "Loques"] 
  et pas [  "Parasites",  "Pesticides",  "Predateurs",  "Loques"]
  j'ai testé Object.values(Vulnerabilite) et Object.keys(Vulnerabilite); 
  ça ne fait que changer l'ordre des nb vs string dans le tableau
  */

  //en attendant les userService 
  listRecolteur: Array<Recolteur> = new Array<Recolteur>;

  constructor(private formBuilder: FormBuilder, 
    private rucheService: RucheHttpService, 
    private router: Router, //pour rediriger le bouton annuler (methode goToListRuche)
    private routes: ActivatedRoute) // pour récupérer le param //[routerLink]="['/ruche/ma-ruche']" 
    {
    
    //en attendant les userService 
    this.listRecolteur.push(new Recolteur(1, "Huguette", "azerty"));
    this.listRecolteur.push(new Recolteur(2, "GeorgeRecolte", "recolteur"));

    //création du reactive form + validation 
    this.rucheForm = this.formBuilder.group({
      id: this.formBuilder.control(''),
      cadre: this.formBuilder.control('', Validators.required),
      recolteur: this.formBuilder.control('', Validators.required),
      vulnerabilite: this.formBuilder.control('')
    });

    //pré-remplissage si on reçoit un paramètre
    this.routes.params.subscribe(params => { 
      this.rucheForm.patchValue({        
        id: params['id'],
        cadre: 1000,
        vulnerabilite: Vulnerabilite[Vulnerabilite.Loques],
        recolteur: this.listRecolteur[0]
      })
    })
  }
  

formValue: Ruche ; 

  submit(){
    this.formValue = this.rucheForm.value;
    if(this.formValue.id) {
      this.rucheService.update(this.formValue);
    } else {
      this.rucheService.create(this.formValue);
    }

    this.cancel();
  }

  cancel(): void {
    this.formValue = null;
  }
  


  listRecolteurs(){
    return this.listRecolteur; //en attendant d'avoir le userService 
  }

  goToListRuche(){
    this.router.navigate([ '/ruche']);
  }
}
