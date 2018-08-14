/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageDetailsMySuffixUpdateComponent } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix-update.component';
import { EntityPageDetailsMySuffixService } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix.service';
import { EntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageDetailsMySuffix Management Update Component', () => {
        let comp: EntityPageDetailsMySuffixUpdateComponent;
        let fixture: ComponentFixture<EntityPageDetailsMySuffixUpdateComponent>;
        let service: EntityPageDetailsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageDetailsMySuffixUpdateComponent]
            })
                .overrideTemplate(EntityPageDetailsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityPageDetailsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageDetailsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityPageDetailsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityPageDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityPageDetailsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityPageDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
