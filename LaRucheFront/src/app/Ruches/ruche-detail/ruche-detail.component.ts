import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Recolteur, Ruche, RucheRequest, Vulnerabilite } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'ruche-detail',
  templateUrl: './ruche-detail.component.html',
  styleUrls: ['./ruche-detail.component.scss']
})
export class RucheDetailComponent {

  formValue: RucheRequest;
  id: string;
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


    //création du reactive form + validation et pré-remplissage selon si un param est présent 
    //attention à récupérer la bonne ruche
    this.routes.params.subscribe(params => {
      this.id = params['id']

      if(this.id){
        this.formValue = new RucheRequest(1,2,false, Vulnerabilite.Parasites, 1); 
        this.rucheForm = this.formBuilder.group({

          id: this.formBuilder.control(this.formValue.id),
          cadre: this.formBuilder.control(this.formValue.cadre, Validators.required),
          recolteur: this.formBuilder.control(this.formValue.recolteurId, Validators.required),
          vulnerabilite: this.formBuilder.control(Vulnerabilite[this.formValue.vulnerabilite])
          
        });        
      }
      else{
        this.rucheForm = this.formBuilder.group({

          id: this.formBuilder.control(''),
          cadre: this.formBuilder.control('', Validators.required),
          recolteur: this.formBuilder.control('', Validators.required),
          vulnerabilite: this.formBuilder.control('')
          
        });
      }
    })
  }
  
  submit(){
   this.formValue = this.rucheForm.value;
   // this.formValue = new RucheRequest(this.rucheForm.get('id').value, this.rucheForm.get('cadre').value, false, this.rucheForm.get('vulnerabilite').value, this.rucheForm.get('recolteur').value.id);
    if(this.formValue.id) {
      this.rucheService.update(this.formValue);
    } else {
      this.rucheService.create(this.formValue);
    }
    console.log(this.formValue)
    console.log(this.rucheForm.value)
    /*
    problème : ça renvoit un recolteur:"1" au lieu d'un recolteurId : 1 
    => probablement lié au fait que dans html on a value=recolteur.id 
    mais comment faire du coup ? 
    */
    this.cancel();
  }

  cancel(): void {
    this.formValue = null;
  }
  


  listRecolteurs(){
    return this.listRecolteur; //en attendant d'avoir le userService 
  }

  goToListRuche(){
    this.router.navigate([ 'gestionnaire/ruche']);
  }

}
