import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { ProspectPF } from '../shared/prospect.model';

@Component({
  selector: 'app-prospect-pf',
  templateUrl: './prospect-pf.component.html',
  styleUrls: ['./prospect-pf.component.css']
})
export class ProspectPFComponent implements OnInit {
  @Input() prospect: ProspectPF | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
