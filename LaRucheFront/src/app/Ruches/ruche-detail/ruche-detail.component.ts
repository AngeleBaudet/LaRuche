import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Recolteur, Ruche, RucheRequest, Vulnerabilite } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';
import { UserHttpService } from 'src/app/User/user-http.service';

@Component({
  selector: 'ruche-detail',
  templateUrl: './ruche-detail.component.html',
  styleUrls: ['./ruche-detail.component.scss']
})
export class RucheDetailComponent {
  //[routerLink]="['gestionnaire/ruche/ma-ruche']" 

  rucheForm: FormGroup;
  formValue: RucheRequest = new RucheRequest(); //initialiser sinon le html se perd

  vulnerabilite = Object.values(Vulnerabilite).filter(value => isNaN(Number(value)));
  /* Obligé de mettre le isNaN filtre car sinon il me retourne  
  [   0,  1,  2,  3, "Parasites",  "Pesticides",  "Predateurs",  "Loques"] 
  et pas [  "Parasites",  "Pesticides",  "Predateurs",  "Loques"]
  j'ai testé Object.values(Vulnerabilite) et Object.keys(Vulnerabilite); 
  ça ne fait que changer l'ordre des nb vs string dans le tableau
  */

  
  constructor(private formBuilder: FormBuilder, 
    private rucheService: RucheHttpService, 
    private router: Router, //pour rediriger le bouton annuler (methode goToListRuche)
    private routes: ActivatedRoute, // pour récupérer le param 
    private userService: UserHttpService)
    {

    //création du reactive form (+validation)
    this.rucheForm = this.formBuilder.group({
      id: this.formBuilder.control(''),
      cadre: this.formBuilder.control('', Validators.required),
      recolteurId: this.formBuilder.control('', Validators.required),
      vulnerabilite: this.formBuilder.control('')
              
    })

    //pré-remplissage selon si un param est présent (sans le if, il confond les 2 routes)
    this.routes.params.subscribe(params => {
      if(params['id']){
        this.edit(params['id']);
      }
    })
  }

  
  submit(){
    this.formValue = this.rucheForm.value;
   
   //on vérifie si le champs du ReactiveForm égale null pour éviter les mauvaises post requests
    if(this.rucheForm.get('vulnerabilite').value === 'null'){
      this.formValue.vulnerabilite = null;
    }
    
    if(this.formValue.id) {      
      this.rucheService.update(this.formValue);
      this.router.navigate([ 'unbeelievable/ruche'])
    } else {
      this.rucheService.create(this.formValue);
      this.router.navigate([ 'unbeelievable/ruche'])
    }
    this.cancel();
  }

  cancel(): void {
    this.formValue = new RucheRequest();
  }

  edit(id: number): void {
    this.rucheService.findById(id).subscribe(resp => {
      this.formValue = new RucheRequest(resp.id, resp.cadre,resp.limite,resp.vulnerabilite,resp.recolteur.id);
      
      this.rucheForm = this.formBuilder.group({

        id: this.formBuilder.control(this.formValue.id),
        cadre: this.formBuilder.control(this.formValue.cadre, Validators.required),
        recolteurId: this.formBuilder.control(this.formValue.recolteurId, Validators.required),
        vulnerabilite: this.formBuilder.control(this.formValue.vulnerabilite)
        
      });   
    });
  }

  listRecolteurs(){
    return this.userService.findAllRecolteurs();
  }

  goToListRuche(){
    this.router.navigate([ 'unbeelievable/ruche']);
  }


}
