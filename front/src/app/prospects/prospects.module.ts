import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProspectsComponent } from './prospects.component';
import { ProspectPFComponent } from './prospect-pf/prospect-pf.component';
import { ProspectPJComponent } from './prospect-pj/prospect-pj.component';
import { HttpClientModule } from '@angular/common/http';
import { ProspectService } from './prospect.service';

@NgModule({
  declarations: [
    ProspectsComponent,
    ProspectPFComponent,
    ProspectPJComponent,
  ],
  providers: [
    ProspectService,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  exports: [
    ProspectsComponent,
  ],
})
export class ProspectsModule { }
