import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { DossierSocial } from "@models/dossierSocial.model";
import { ECategorie } from '@models/enum/ecategorie.enum';
import { DossierSocialService } from '@services/dossierSocial.service';

import { HttpResponse } from '@angular/common/http';
import { Profil } from '@models/profil.model';
import { ToastService } from '@services/toast.service';

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
    public toastService: ToastService
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
          // this.toastService.show('Le dossier '+dossier.nom+' a été mis à jour', { classname: 'bg-success text-light', delay: 1000 });
        }
      );
    } else {
      this.dossierSocialService.create(dossier).subscribe(
        (response: HttpResponse<DossierSocial>) => {
          this.activeModal.close(response.body);
          // this.toastService.show('Le dossier '+dossier.nom+' a été créé', { classname: 'bg-success text-light', delay: 1000 });
        }
      );
    }
  }

  public onClose(): void {
    this.activeModal.close();
  }

}
