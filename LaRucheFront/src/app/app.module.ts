import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { GlobalService } from './global.service';
import { RucheHttpService } from './Ruches/ruche-http.service';
import { AccueilComponent } from './accueil/accueil.component';
import { ListeRuchesComponent } from './Ruches/liste-ruches/liste-ruches.component';
import { RucheDetailComponent } from './Ruches/ruche-detail/ruche-detail.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { CompteComponent } from './compte/compte.component';
import { NourrissageComponent } from './nourrissage/nourrissage.component';
import { PopulationsComponent } from './populations/populations.component';
import { RecoltesComponent } from './recoltes/recoltes.component';
import { RecolteursComponent } from './recolteurs/recolteurs.component';
import { ClientsComponent } from './clients/clients.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    ListeRuchesComponent,
    RucheDetailComponent,
    AccueilComponent,
    ConnexionComponent,
    CompteComponent,
    NourrissageComponent,
    PopulationsComponent,
    RecoltesComponent,
    RecolteursComponent,
    ClientsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [GlobalService, RucheHttpService],
  bootstrap: [AppComponent],
})
export class AppModule {}
