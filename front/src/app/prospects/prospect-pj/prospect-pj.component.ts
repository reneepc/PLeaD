import { Component, Input, OnInit } from '@angular/core';
import { ProspectPJ } from '../shared/prospect.model';

@Component({
  selector: 'app-prospect-pj',
  templateUrl: './prospect-pj.component.html',
  styleUrls: ['./prospect-pj.component.css']
})
export class ProspectPJComponent implements OnInit {
  @Input() prospect: ProspectPJ | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
