import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { AccueilComponent } from './accueil/accueil.component';

const routes: Routes = [
  {path: "gestionnaire/ruche", component: ListeRuchesComponent},
  {path: "accueil", component: AccueilComponent},
  {path: "gestionnaire/ruche/ma-ruche", component: RucheDetailComponent},
];

@NgModule({
  declarations : [ListeRuchesComponent,RucheDetailComponent,AccueilComponent],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
