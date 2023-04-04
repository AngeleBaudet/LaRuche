import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { AccueilComponent } from './accueil/accueil.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { ListeProductionComponent } from './Production/liste-production/liste-production.component';
import { ProductionDetailComponent } from './Production/production-detail/production-detail.component';
import { UserDetailComponent } from './User/user-detail/user-detail.component';
import { AuthGuard } from './auth.guard';
import { ListeUserComponent } from './User/liste-user/liste-user.component';

const routes: Routes = [
  {
    path: 'gestionnaire',
    component: AccueilComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/ruche/ma-ruche',
    component: RucheDetailComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/ruche/ma-ruche/:id',
    component: RucheDetailComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/ruche',
    component: ListeRuchesComponent,
    //canActivate: [AuthGuard],
  },
  
  { path: 'gestionnaire/ruche/:vuln', component: ListeRuchesComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'gestionnaire/users/usersChange/:id', component:UserDetailComponent}
  {
    path: 'gestionnaire/production',
    component: ListeProductionComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/production/ma-production',
    component: ProductionDetailComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/production/ma-production/:id',
    component: ProductionDetailComponent,
    //canActivate: [AuthGuard],
  },
  {
    path: 'gestionnaire/utilisateurs',
    component: ListeUserComponent,
    //canActivate: [AuthGuard],
  },
  { path: 'gestionnaire/users/usersChange', component:UserDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
