import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ruche, Vulnerabilite } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'ruche-detail',
  templateUrl: './ruche-detail.component.html',
  styleUrls: ['./ruche-detail.component.scss']
})
export class RucheDetailComponent implements OnInit {
  
  cetteRuche : Ruche = new Ruche();
  rucheForm: FormGroup;
  vulnerabilite = Object.values(Vulnerabilite); //me donne aussi les keys???? pourquoi ???
  //La fonctionnalité CORB (Cross-Origin Read Blocking) a bloqué la réponse multi-origine https://fonts.google.com/specimen/Poppins ayant le type MIME text/html. Pour en savoir plus, rendez-vous sur https://www.chromestatus.com/feature/5629709824032768.
  
  constructor(private formBuilder: FormBuilder, private rucheService: RucheHttpService) {}

  ngOnInit(): void {
    this.rucheForm = this.formBuilder.group({
      id: this.formBuilder.control(''),
      cadre: this.formBuilder.control('', Validators.required),
      recolteur: this.formBuilder.control('')
    });
  }

  submit(){
    this.rucheForm.get('id').setValue(1);
    this.rucheForm.get('cadre').setValue(2);
  }

  listRecolteurs(){
    return this.rucheService.findAll(); //en attendant d'avoir les récolteur :x 
  }

}
