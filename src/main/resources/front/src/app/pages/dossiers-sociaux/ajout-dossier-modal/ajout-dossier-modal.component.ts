import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { ToastrService } from 'ngx-toastr';

import { DossierSocial } from "@models/dossierSocial.model";
import { ECategorie } from '@models/enum/ecategorie.enum';
import { DossierSocialService } from '@services/dossierSocial.service';

import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Profil } from '@models/profil.model';

@Component({
  selector: 'app-ajout-dossier-modal',
  templateUrl: './ajout-dossier-modal.component.html',
  styleUrls: ['./ajout-dossier-modal.component.scss']
})
export class AjoutDossierModalComponent implements OnInit {
  public dossier: DossierSocial;

  public categories = ECategorie;
  public categoriesOptions = Object.keys(this.categories);

  public ajoutDossierForm: FormGroup = this.formBuilder.group(
    {
      id: new FormControl(null),
      nom: new FormControl('', Validators.required),
      categorie: new FormControl('', Validators.required)
    }
  );

  constructor(
    protected activeModal: NgbActiveModal,
    protected formBuilder: FormBuilder,
    protected dossierSocialService: DossierSocialService,
    protected toastService: ToastrService
  ) {
  }

  public ngOnInit(): void {
    if (this.dossier) {
      this.ajoutDossierForm.patchValue({
        id: this.dossier.id,
        nom: this.dossier.nom,
        categorie: this.dossier.categorie
      });
    }
  }

  public accept(): void {
    let dossier: DossierSocial = {
      id: this.ajoutDossierForm.value.id,
      nom: this.ajoutDossierForm.value.nom,
      categorie: this.ajoutDossierForm.value.categorie,
      profils: new Array<Profil>(0)
    };

    if (dossier.id) {
      this.dossierSocialService.update(dossier).subscribe(
        (response: HttpResponse<DossierSocial>) => {
          this.activeModal.close(response.body);
          this.toastService.success('Le dossier '+dossier.nom+' a été mis à jour', 'Modification du dossier');
        },
        (response: HttpErrorResponse) => {
          this.toastService.error(response.error.error, response.error.message);
        }
      );
    } else {
      this.dossierSocialService.create(dossier).subscribe(
        (response: HttpResponse<DossierSocial>) => {
          this.activeModal.close(response.body);
          this.toastService.success('Le dossier '+dossier.nom+' a été créé', 'Création du dossier');
        },
        (response: HttpErrorResponse) => {
          this.toastService.error(response.error.error, response.error.message);
        }
      );
    }
  }

  public onClose(): void {
    this.activeModal.close();
  }

}
