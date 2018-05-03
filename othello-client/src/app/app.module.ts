import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './loginPlayer/login.component';
import { CreateAccount } from './services/createPlayerAccount/createAccount.service';
import { CreateAccountComponent } from './services/createPlayerAccount/createAccount.component';
import { GameService } from './services/game/game.service';
import { BoardComponent } from './entities/board/component/board.component';
import { DiscComponent } from './entities/disc/component/disc.component';
import { ScoreComponent } from './entities/score/score.component';

import { routing } from './app.routing';


@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    DiscComponent,
    ScoreComponent,
    LoginComponent,
    CreateAccountComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    routing
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
