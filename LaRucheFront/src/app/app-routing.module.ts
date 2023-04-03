import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { AccueilComponent } from './accueil/accueil.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { CompteComponent } from './compte/compte.component';
import { NourrissageComponent } from './nourrissage/nourrissage.component';
import { PopulationsComponent } from './populations/populations.component';
import { RecoltesComponent } from './recoltes/recoltes.component';
import { RecolteursComponent } from './recolteurs/recolteurs.component';
import { ClientsComponent } from './clients/clients.component';

const routes: Routes = [
  { path: 'connexion', component: ConnexionComponent, pathMatch: 'full'},
  { path: 'gestionnaire', component: AccueilComponent},
  { path: 'gestionnaire/ruche', component: ListeRuchesComponent },
  { path: 'gestionnaire/ruche/ma-ruche', component: RucheDetailComponent },
  { path: 'gestionnaire/ruche/ma-ruche/:id', component: RucheDetailComponent },
  { path: 'gestionnaire/compte',component:CompteComponent},
  { path: 'gestionnaire/nourrissage', component:NourrissageComponent},
  { path: 'gestionnaire/populations', component:PopulationsComponent},
  { path: 'gestionnaire/recoltes', component:RecoltesComponent},
  { path: 'gestionnaire/recolteurs', component:RecolteursComponent},
  { path: 'gestionnaire/clients', component:ClientsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
