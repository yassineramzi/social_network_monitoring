import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { DossierSocial } from '@models/dossierSocial.model';

@Component({
  selector: 'app-dossiers-sociaux',
  templateUrl: './dossiers-sociaux-page.component.html',
  styleUrls: ['./dossiers-sociaux-page.component.scss']
})
export class DossiersSociauxPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(2)
    }
  );

  public dossiersArrayPage: Array<DossierSocial> = [];

  public dossiersPage: number = 1;

  public dossiersCollectionSize: number = 0;

  public pageSize: number = 4;

  private dossiersArray: Array<DossierSocial> = [];

  constructor(
      protected formBuilder: FormBuilder,
    ) { }

  ngOnInit(): void {
  }

  public refreshDossiers(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.dossiersArrayPage = this.dossiersArray
      .slice((this.dossiersPage - 1) * this.pageSize, (this.dossiersPage - 1) * this.pageSize + this.pageSize);
    this.dossiersCollectionSize = this.dossiersArray.length;
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }
}
