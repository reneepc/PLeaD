import { Component, OnInit } from '@angular/core';

import { ProspectService } from './prospect.service';
import { ProspectPF, ProspectPJ } from './shared/prospect.model';

@Component({
  selector: 'app-prospects',
  templateUrl: './prospects.component.html',
  styleUrls: ['./prospects.component.css']
})
export class ProspectsComponent implements OnInit {
  prospectsPF: ProspectPF[] = [];
  prospectsPJ: ProspectPJ[] = [];

  constructor(private prospectService: ProspectService) { }

  ngOnInit(): void {
    this.prospectService.getAllProspects()
      .subscribe((data: any) => {
        for(let prospect of data.content) {
          if(prospect.hasOwnProperty('cnpj')) {
            this.prospectsPJ.push(prospect);
            console.log(prospect);
          } else {
            this.prospectsPF.push(prospect);
            console.log(prospect);
          }
        }
      });
  }
}
