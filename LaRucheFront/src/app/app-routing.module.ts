import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { AccueilComponent } from './accueil/accueil.component';
import { ConnexionComponent } from './connexion/connexion.component';

const routes: Routes = [
  { path: 'ruche', component: ListeRuchesComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'ruche/ma-ruche', component: RucheDetailComponent },
  { path: 'connexion', component: ConnexionComponent },
];

@NgModule({
  declarations: [
    ListeRuchesComponent,
    RucheDetailComponent,
    AccueilComponent,
  ],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
