import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { GlobalService } from './global.service';
import { RucheHttpService } from './httpServices/ruche-http.service';

@NgModule({
  declarations: [
    AppComponent, 
    NavbarComponent, 
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    GlobalService,
    RucheHttpService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
