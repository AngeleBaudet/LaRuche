import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Production, ProductionRequest, Produit, Recolteur, Ruche } from 'src/app/model';
import { ProductionHttpService } from '../production-http.service';

@Component({
  selector: 'app-production-detail',
  templateUrl: './production-detail.component.html',
  styleUrls: ['./production-detail.component.scss']
})
export class ProductionDetailComponent {
prodForm:FormGroup;
formValue:ProductionRequest=new ProductionRequest();

//en attendant les userService 
listRecolteur:Array<Recolteur>=new Array<Recolteur>;
listRuche:Array<Ruche>=new Array <Ruche>;

produit=Object.values(Produit).filter(value => isNaN(Number(value)));

constructor(private formBuilder: FormBuilder, 
  private productionService: ProductionHttpService, 
  private router: Router,
  private routes: ActivatedRoute) 
  {

    //en attendant les userService 
    this.listRecolteur.push(new Recolteur(1, "Huguette", "azerty"));
    this.listRecolteur.push(new Recolteur(2, "GeorgeRecolte", "recolteur"));

    this.listRuche.push(new Ruche(1));
    this.listRuche.push(new Ruche(2))

    this.prodForm = this.formBuilder.group({
      id: this.formBuilder.control(''),
      stock: this.formBuilder.control('', Validators.required),
      annee: this.formBuilder.control('', Validators.required),
      ruche: this.formBuilder.control('', Validators.required),
      prixKg: this.formBuilder.control('', Validators.required),
      produit: this.formBuilder.control('', Validators.required),
      recolteur: this.formBuilder.control('', Validators.required)
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
      this.router.navigate(['gestionnaire/production'])
    } else {
      this.productionService.create(this.formValue);
      this.router.navigate(['gestionnaire/production'])
    }
    this.cancel();
  }

  cancel() {
    this.formValue =new ProductionRequest();
  }

  edit(id:number):void {
    this.productionService.findById(id).subscribe(resp => {
      this.formValue=new ProductionRequest(resp.id,resp.stock,resp.annee,resp.ruche.id,resp.prixKg,resp.produit,resp.recolteur.id);
      console.log(this.formValue);
      console.log(resp);
      this.prodForm=this.formBuilder.group({
        id:this.formBuilder.control(this.formValue.id),
        stock:this.formBuilder.control(this.formValue.stock, Validators.required),
        annee:this.formBuilder.control(this.formValue.annee, Validators.required),
        ruche:this.formBuilder.control(this.formValue.rucheId, Validators.required),
        prixKg:this.formBuilder.control(this.formValue.prixKg, Validators.required),
        produit:this.formBuilder.control(this.formValue.produit, Validators.required),
        recolteur:this.formBuilder.control(this.formValue.recolteurId, Validators.required),
      });
    });
  }

  listRuches(){
    return this.listRuche;
  }

  listRecolteurs(){
    return this.listRecolteur;
  }

  goToListProd(){
    this.router.navigate(['gestionnaire/production']);
  }
}
