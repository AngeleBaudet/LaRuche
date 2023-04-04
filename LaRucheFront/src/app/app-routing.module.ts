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
    path: 'unbeelievable',
    component: AccueilComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'unbeelievable/ruche',
    component: ListeRuchesComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'unbeelievable/ruche/ma-ruche',
    component: RucheDetailComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'unbeelievable/ruche/ma-ruche/:id',
    component: RucheDetailComponent,
    canActivate: [AuthGuard],
  },

  { path: 'unbeelievable/ruche/:vuln', component: ListeRuchesComponent },

  { path: 'unbeelievable/ruche/:nourrir', component: ListeRuchesComponent },

  { path: 'connexion', component: ConnexionComponent },

  {
    path: 'unbeelievable/utilisateurs',
    component: ListeUserComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'unbeelievable/utilisateurs/mon-utilisateur',
    component: UserDetailComponent,
  },

  {
    path: 'unbeelievable/utilisateurs/mon-utilisateur/:id',
    component: UserDetailComponent,
  },

  {
    path: 'unbeelievable/production',
    component: ListeProductionComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'unbeelievable/production/ma-production',
    component: ProductionDetailComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'unbeelievable/production/ma-production/:id',
    component: ProductionDetailComponent,
    canActivate: [AuthGuard],
  },

  {path: 'unbeelievable/utilisateurs', component:ListeUserComponent,canActivate: [AuthGuard],},

  {path: 'unbeelievable/utilisateurs/recolteurs', component:ListeUserComponent,canActivate: [AuthGuard],},

  {path: 'unbeelievable/utilisateurs/clients', component:ListeUserComponent,canActivate: [AuthGuard],}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
