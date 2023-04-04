import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Production, ProductionRequest, Produit, Recolteur, Ruche, User } from 'src/app/model';
import { ProductionHttpService } from '../production-http.service';
import { RucheHttpService } from 'src/app/Ruches/ruche-http.service';
import { UserHttpService } from 'src/app/User/user-http.service';
import { ConnexionHttpService } from 'src/app/connexion/connexion-http.service';

@Component({
  selector: 'app-production-detail',
  templateUrl: './production-detail.component.html',
  styleUrls: ['./production-detail.component.scss']
})
export class ProductionDetailComponent {
prodForm:FormGroup;
formValue:ProductionRequest=new ProductionRequest();

//en attendant les userService 

produit=Object.values(Produit).filter(value => isNaN(Number(value)));

constructor(private formBuilder: FormBuilder, 
  private productionService: ProductionHttpService, 
  private router: Router,
  private routes: ActivatedRoute, 
  private rucheService: RucheHttpService, 
  private userService: UserHttpService,
  private connexionService: ConnexionHttpService) 
  {
    this.prodForm = this.formBuilder.group({
      id: this.formBuilder.control(''),
      stock: this.formBuilder.control('', Validators.required),
      annee: this.formBuilder.control('', Validators.required),
      rucheId: this.formBuilder.control('', Validators.required),
      prixKg: this.formBuilder.control('', Validators.required),
      produit: this.formBuilder.control('', Validators.required),
      recolteurId: this.formBuilder.control('', Validators.required)
    })
     
    this.routes.params.subscribe(params => {
      if(params['id']){
        this.edit(params['id']);
      }
    })
  }

  /*----------------*/

  save() {
    this.formValue=this.prodForm.value;
    if(this.prodForm.get('produit').value =='null'){
      this.formValue.produit=null;
    }
    if(this.formValue.id){
      this.productionService.update(this.formValue);
      this.router.navigate(['unbeelievable/production'])
    } else {
      this.productionService.create(this.formValue);
      this.router.navigate(['unbeelievable/production'])
    }
    this.cancel();
  }

  cancel() {
    this.formValue =new ProductionRequest();
  }

  edit(id:number):void {
    this.productionService.findById(id).subscribe(resp => {
      this.formValue=new ProductionRequest(resp.id,resp.stock,resp.annee,resp.ruche.id,resp.produit,resp.prixKg,resp.recolteur.id);

      console.log(this.formValue);
      console.log(resp);
      this.prodForm=this.formBuilder.group({
        id:this.formBuilder.control(this.formValue.id),
        stock:this.formBuilder.control(this.formValue.stock, Validators.required),
        annee:this.formBuilder.control(this.formValue.annee, Validators.required),
        rucheId:this.formBuilder.control(this.formValue.rucheId, Validators.required),
        prixKg:this.formBuilder.control(this.formValue.prixKg, Validators.required),
        produit:this.formBuilder.control(this.formValue.produit, Validators.required),
        recolteurId:this.formBuilder.control(this.formValue.recolteurId, Validators.required),
      });
    });
  }

  listRuches(){
    return this.rucheService.findAll();
  }

  listRecolteurs(){
    if (this.connexionService.allowed()){
      return this.userService.findAllRecolteurs();
    }
    return new Array<User>(this.connexionService.connectedUser)
  }

  goToListProd(){
    this.router.navigate(['unbeelievable/production']);
  }
}
