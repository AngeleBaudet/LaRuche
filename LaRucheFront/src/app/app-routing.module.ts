import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { AccueilComponent } from './accueil/accueil.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { ListeProductionComponent } from './Production/liste-production/liste-production.component';
import { ProductionDetailComponent } from './Production/production-detail/production-detail.component';

const routes: Routes = [
  { path: 'gestionnaire', component: AccueilComponent, pathMatch: 'full'},
//  { path: 'accueil', component: AccueilComponent, pathMatch: 'full' },
  { path: 'gestionnaire/ruche', component: ListeRuchesComponent },
  { path: 'gestionnaire/ruche/:vuln', component: ListeRuchesComponent },
  { path: 'gestionnaire/ruche/ma-ruche', component: RucheDetailComponent },
  { path: 'gestionnaire/ruche/ma-ruche/:id', component: RucheDetailComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'gestionnaire/production', component:ListeProductionComponent},
  { path: 'gestionnaire/production/ma-production', component:ProductionDetailComponent},
  { path: 'gestionnaire/production/ma-production/:id', component:ProductionDetailComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
