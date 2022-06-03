import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProspectService {
  apiUrl: string = "http://localhost:8080/prospects";

  constructor(private http: HttpClient) { }

  getAllProspects(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}