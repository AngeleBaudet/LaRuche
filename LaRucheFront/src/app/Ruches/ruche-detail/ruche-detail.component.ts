import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ruche } from 'src/app/model';
import { RucheHttpService } from '../ruche-http.service';

@Component({
  selector: 'ruche-detail',
  templateUrl: './ruche-detail.component.html',
  styleUrls: ['./ruche-detail.component.scss']
})
export class RucheDetailComponent implements OnInit {
  
  cetteRuche : Ruche;
  rucheForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private rucheService: RucheHttpService) {}

  ngOnInit(): void {
    this.rucheForm = this.formBuilder.group({
      email: this.formBuilder.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.control('', [Validators.required, Validators.minLength(8)]),
      passwordConfirm: this.formBuilder.control('', Validators.required)
    });
  }

  submit(){}

  list(){
    return this.rucheService.findAll();
  }

}
