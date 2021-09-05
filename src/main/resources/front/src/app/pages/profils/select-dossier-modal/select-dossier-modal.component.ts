import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from "@angular/router";
import { HttpResponse } from '@angular/common/http';

import { DossierSocial } from '@models/dossierSocial.model';
import { DossierSocialService } from '@services/dossierSocial.service';

@Component({
  selector: 'app-select-dossier-modal',
  templateUrl: './select-dossier-modal.component.html'
})
export class SelectDossierModalComponent implements OnInit {

  public selectDossierModalForm : FormGroup = this.formBuilder.group(
    {
      idDossier : new FormControl(null, Validators.required),
    }
  );

  public dossiers: DossierSocial[] = [];

  constructor(
    protected formBuilder: FormBuilder,
    protected activeModal: NgbActiveModal,
    protected router : Router,
    protected dossierService: DossierSocialService
  ) { }

  ngOnInit(): void {
    this.dossierService.findAll().subscribe(
      (response: HttpResponse<DossierSocial[]>) => {
        this.dossiers = response.body || [];
      }
    );
  }

  public accept(): void {
    this.activeModal.close();
    this.router.navigate(['/profils-page/'+this.selectDossierModalForm.get('idDossier').value+'/dossier']);
  }

  close() {
    this.activeModal.dismiss('cancel');
    this.router.navigate(['/dossiers-sociaux-page']);
  }
}
